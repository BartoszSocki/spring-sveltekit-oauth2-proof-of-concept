import { Sequelize, DataTypes } from 'sequelize'
import { DB_USER, DB_PASSWORD, DB_URL, DB_NAME } from '$env/static/private'
import { logger } from '$lib/server/Logger'

export const sequelize = new Sequelize(`postgres://${DB_USER}:${DB_PASSWORD}@${DB_URL}/${DB_NAME}`, {
    logging: false
})

export const User = sequelize.define('User', {
    id: {
        type: DataTypes.UUID,
        defaultValue: Sequelize.UUIDV4,
        allowNull: false,
        primaryKey: true
    },
    email: {
        type: DataTypes.TEXT,
        allowNull: false,
        validate: {
            isEmail: {
                msg: 'provided value is not an email'
            }
        }
    },
    accessToken: {
        type: DataTypes.TEXT
    },
    refreshToken: {
        type: DataTypes.TEXT
    }
}, {
    schema: 'sveltekit',
    tableName: 'users',
    underscored: true
});

export const Session = sequelize.define('Session', {
    id: {
        type: DataTypes.STRING(32),
        allowNull: false,
        primaryKey: true
    },
    ipAddress: {
        type: DataTypes.TEXT,
        defaultValue: null,
        validate: {
            isIP: {
                msg: 'provided value is not an ip address'
            }
        }
    },
    userId: {
        type: DataTypes.UUID,
        allowNull: false,
        references: {
            model: User,
            key: 'id'
        }
    },
    state: {
        type: DataTypes.TEXT
    },
    expirationDate: {
        type: DataTypes.DATE,
        allowNull: false
    },
    expirationDateIdle: {
        type: DataTypes.DATE,
        allowNull: false
    },
    isSessionExpired: {
        type: DataTypes.VIRTUAL,
        get() {
            const now = Date.now()
            const expirationDate = this.expirationDate

            return expirationDate > now
        }
    },
    isSessionDead: {
        type: DataTypes.VIRTUAL,
        get() {
            const now = Date.now()
            const expirationDateIdle = this.expirationDateIdle

            return expirationDateIdle >= now
        }
    }
}, {
    schema: 'sveltekit',
    tableName: 'sessions',
    underscored: true,
    hooks: {
        afterCreate: (session, options) => {
            logger.info(`session created: ${session.id} for user: ${session.userId}, expires at: ${session.expirationDate}`)
        },
        beforeDestroy: (session, options) => {
            logger.info(`session deleted: ${session.id} for user: ${session.userId}`)
        }
    }
})

export const anonymousUserId = 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'

User.sync()
Session.sync()