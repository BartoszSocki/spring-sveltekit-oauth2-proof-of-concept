export const positiveIntegerRegex = /^[0-9]\d*$/
export const simpleStringRegex = /^[ \w]+$/

export function isSimpleString(value) {
    return value && simpleStringRegex.test(value)
}