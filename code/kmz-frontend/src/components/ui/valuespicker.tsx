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
    values,
    onChange,
    valueLabel = "Add value",
}: ValuePickerProps) => {
    // store all selected values as an array instead of indexed object
    const [pickedValues, setPickedValues] = useState<string[]>([]);

    useEffect(() => {
        onChange(pickedValues);
    }, [pickedValues, onChange]);

    const handleSelectChange = (index: number, newValue: string) => {
        setPickedValues((prev) => {
            const updated = [...prev];
            updated[index] = newValue;
            return updated;
        });
    };

    const handleRemove = (index: number) => {
        setPickedValues((prev) => prev.filter((_, i) => i !== index));
    };

    const handleAdd = () => {
        // add a new empty slot
        setPickedValues((prev) => [...prev, ""]);
    };

    const availableValues = (index: number) =>
        values.filter(
            (v) => !pickedValues.includes(v) || pickedValues[index] === v
        );

    return (
        <div className="flex flex-col gap-2">
            {label && (
                <div>
                    <label className="font-medium text-sm">{label}</label>
                </div>
            )}

            <div className="flex flex-col">
                {pickedValues.map((value, index) => (
                    <div className="flex gap-2 mb-2 items-center" key={index}>
                        <Select
                            value={value}
                            onValueChange={(v) => handleSelectChange(index, v)}
                        >
                            <SelectTrigger>
                                <SelectValue placeholder="Select value" />
                            </SelectTrigger>
                            <SelectContent>
                                {availableValues(index).map((v) => (
                                    <SelectItem key={v} value={v}>
                                        {v}
                                    </SelectItem>
                                ))}
                            </SelectContent>
                        </Select>

                        <Button
                            variant="default"
                            size="icon-sm"
                            onClick={() => handleRemove(index)}
                        >
                            <Trash2 size={16} />
                        </Button>
                    </div>
                ))}
            </div>

            <Button variant="outline_default" size="sm" onClick={handleAdd}>
                {valueLabel}
            </Button>
        </div>
    );
};

export default ValuesPicker;
