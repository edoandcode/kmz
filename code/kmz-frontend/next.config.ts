import type { NextConfig } from "next";

const isDev = process.env.NODE_ENV !== 'production';

const nextConfig: NextConfig = {
  /* config options here */
  compiler: {
    removeConsole: false,
  }
};

export default nextConfig;
