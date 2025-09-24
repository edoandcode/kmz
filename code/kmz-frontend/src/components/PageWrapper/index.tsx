import clsx from 'clsx';
import React, { ReactNode, ElementType, ComponentPropsWithoutRef } from 'react';

type PageWrapperProps<T extends ElementType = 'main'> = {
    children: ReactNode;
    as?: T;
    spaceTop?: boolean,
    spaceBottom?: boolean
} & ComponentPropsWithoutRef<T>;

const PageWrapper = <T extends ElementType = 'main'>({
    children,
    as,
    spaceTop = true,
    spaceBottom = true,
    className,
    ...props
}: PageWrapperProps<T>) => {
    const Component = as || 'main';
    return (
        <Component
            className={clsx(
                'page-wrapper',
                'pt-[var(--header-height)]',
                'max-w-svw',
                className
            )}
            {...props}
        >
            {spaceTop ? <div className="w-full h-10 spacer"></div> : null}
            {children}
            {spaceBottom ? <div className="w-full h-15 spacer"></div> : null}
        </Component>
    );
};

export default PageWrapper;