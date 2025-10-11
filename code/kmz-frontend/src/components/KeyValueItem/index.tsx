import React from 'react';

const KeyValueItem = ({ label, value }: { label: string; value: string | number }) => {
    return (
        <p className='font-medium text-sm'>{label}: <span className='font-normal'>{value}</span></p>

    )
}

export default KeyValueItem