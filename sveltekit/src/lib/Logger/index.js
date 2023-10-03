import { transports, format, createLogger } from 'winston'

export const logger = createLogger({
    level: 'silly',
    transports: [
        //
        // - Write all logs with importance level of `error` or less to `error.log`
        // - Write all logs with importance level of `info` or less to `combined.log`
        //
        // new winston.transports.File({ filename: 'error.log', level: 'error' }),
        // new winston.transports.File({ filename: 'combined.log' }),
        new transports.Console({})
      ],
      format: format.combine(
        format.colorize(),
        format.simple(),
        format.timestamp({
          format: "MMM-DD-YYYY HH:mm:ss",
        }),
      )
})
