import React from 'react';

import clsx from 'clsx';
import _ from 'lodash';

const FormErrorMessage = ({ error }: { error: object | undefined }) => {

    const message = _.find(
        _.flatMapDeep(error, (value, key) =>
            key === "message" ? [value] : _.isObject(value) ? value : []
        ),
        _.isString
    )


    return (
        <div className={clsx(
            'text-xs text-red-600',
            'absolute -bottom-4 left-0',
        )}
        >{message}</div>
    )
}

export default FormErrorMessage