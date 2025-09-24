import React from 'react'
import clsx from 'clsx'
import Grid from '@/components/Grid'
import Logo from '@/components/Logo'

const Header = () => {
    return (
        <header className={clsx(
            "w-full fixed top-0 left-0 z-1000 py-3 border-b border-b-neutral-100 shadow-md",
        )}>
            <Grid >
                <Grid.Col span={3}>
                    <Logo size="50px"></Logo>
                </Grid.Col>
            </Grid>
        </header>
    )
}

export default Header