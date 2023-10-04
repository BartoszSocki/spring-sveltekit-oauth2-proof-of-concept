import { transports, format, createLogger } from 'winston'
import { LOG_LEVEL } from '$env/static/private'

export const logger = createLogger({
    level: LOG_LEVEL || 'info',
    defaultMeta: {
      service: 'logger'
    },
    transports: [
        new transports.Console({})
      ],
      format: format.combine(
        format.colorize({ level: true }),
        format.timestamp({ format: 'YYYY-MM-DD HH:mm:ss.SSS' }),
        format.align(),
        format.printf((info) => `[${info.timestamp}] ${info.level}: ${info.message}`)
        ),
})
