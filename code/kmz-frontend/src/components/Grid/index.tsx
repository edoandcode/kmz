import type { FC } from 'react';
import { clsx } from 'clsx';
import { Grid as UiKitGrid } from "@edoandcode/ui-kit-grid";
import type { GridProps, ColProps } from '@edoandcode/ui-kit-grid';
import defaultGridConfig from './../../../ui-kit-grid-config.js';
import GridDebug from './GridDebug';

interface GridComponentProps extends Omit<GridProps, 'config'> {
    boxed?: boolean;
    config?: GridProps['config'];
}

const Grid: FC<GridComponentProps> & { Col: FC<ColProps> } = ({
    children,
    className,
    config = defaultGridConfig,
    boxed,
    ...rest
}) => {
    return (
        <UiKitGrid
            config={config}
            className={clsx(
                "3xl:max-w-[1920px] 3xl:mx-auto",
                boxed && "max-w-[1180px] lg:mx-auto",
                className
            )}
            {...rest}
        >
            {children}
        </UiKitGrid>
    );
};

Grid.Col = UiKitGrid.Col;

export default Grid;

export { GridDebug };