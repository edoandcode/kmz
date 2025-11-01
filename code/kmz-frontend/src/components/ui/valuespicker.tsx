import React, { useEffect, useState } from 'react';

import { Trash2 } from 'lucide-react';

import { Button } from './button';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from './select';

interface ValuePickerProps {
    label?: string;
    values: string[];
    onChange: (value: string[]) => void;
    valueLabel?: string;
}


const ValuesPicker = ({
    label,
    onChange,
    values,
    valueLabel = "Add value",
}: ValuePickerProps) => {

    const [valuesCounter, setValuesCounter] = useState<number>(1);
    const [pickedValues, setPickedValues] = useState<string[]>([]);

    useEffect(() => {
        onChange(pickedValues);
        //console.log('Picked values:', pickedValues);
    }, [pickedValues, onChange]);

    return (
        <div className='flex flex-col gap-2'>
            <div>
                <label>{label}</label>
            </div>
            <div>
                {new Array(valuesCounter).fill(null).map((_, index) => (
                    <div
                        className='flex gap-2 mb-2 items-center'
                        key={index}
                    >
                        <Select
                            key={index}
                            onValueChange={v => setPickedValues(prev => {
                                if (prev.includes(v)) return prev;
                                const newValues = [...prev];
                                newValues.push(v);
                                return newValues;
                            })}
                        >
                            <SelectTrigger>
                                <SelectValue placeholder="Select a value" />
                            </SelectTrigger>
                            <SelectContent>
                                {values.filter(v => !pickedValues.includes(v)).map((value) => (
                                    <SelectItem key={value} value={value}>{value}</SelectItem>
                                ))}
                            </SelectContent>
                        </Select>
                        <Button
                            variant="default"
                            size={"icon-sm"}
                            onClick={() => {
                                setPickedValues(prev => prev.filter((_, i) => i !== index));
                                setValuesCounter(prev => Math.max(0, prev - 1));
                            }}
                        >
                            <Trash2 size={16} />
                        </Button>
                    </div>
                ))}
            </div>
            <Button
                variant={"outline_default"}
                size={"sm"}
                onClick={() => setValuesCounter(valuesCounter + 1)}
            >{valueLabel}</Button>
        </div>
    )
}

export default ValuesPicker