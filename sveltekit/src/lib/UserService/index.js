import { User } from '$lib/db.js'
import { PROD } from '$env/static/private'

export async function createUser({ email, accessToken, refreshToken }) {
    return await User.create({ 
        email,
        accessToken: accessToken || null,
        refreshToken: refreshToken || null
    }, {
       skip: [PROD !== undefined && PROD === 'true' ? '' : 'email'] 
    })
}

export async function findUserByEmail({ email }) {
    const users = await User.find({ where: { email } })
    if (users === null || users.length === 0) {
        return null
    }

    return users.at(0)
}

export async function findUserById({ id }) {
    const user = await User.findByPk(id)
    return user || null
}