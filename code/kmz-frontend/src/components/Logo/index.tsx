'use client'
import React, { CSSProperties } from 'react';

import clsx from 'clsx';
import Image from 'next/image';
import Link from 'next/link';

import { ROUTES } from '@/settings/routes';

interface LogoProps {
    size?: CSSProperties['width']
}

const Logo = ({ size = '200px' }: LogoProps) => {
    return (
        <Link
            href={`/${ROUTES.HOME}`}
            className={clsx(
                "block aspect-square relative"
            )}
            style={{ width: size, height: size }}
        >
            <Image
                src="/kmz-logo.png"
                alt="KMZ Logo"
                fill
                className="object-cover absolute"
            />
        </Link>
    )
}

export default Logo