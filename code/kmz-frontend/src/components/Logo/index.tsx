'use client'
import React, { CSSProperties } from 'react';

import clsx from 'clsx';
import Image from 'next/image';

interface LogoProps {
    size?: CSSProperties['width']
}

const Logo = ({ size = '200px' }: LogoProps) => {
    return (
        <div className={clsx(
            "aspect-square relative"
        )}
            style={{ width: size, height: size }}
        >
            <Image
                src="/kmz-logo.png"
                alt="KMZ Logo"
                fill
                className="object-cover absolute"
            />
        </div>
    )
}

export default Logo