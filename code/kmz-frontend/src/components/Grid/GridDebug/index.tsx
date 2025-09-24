'use client'
import React from 'react'
import defaultGridConfig from './../../../../ui-kit-grid-config.js';
import { GridDebug as UiKitGridDebug } from "@edoandcode/ui-kit-grid";

const GridDebug = () => {
    return (
        <UiKitGridDebug config={defaultGridConfig}></UiKitGridDebug>
    )
}

export default GridDebug