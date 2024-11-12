import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  /* config options here */
  output: "export",  // <=== enables static exports
  basePath: '/p5566123321.github.io',
  images: {
    unoptimized: true,
  },
};

export default nextConfig;
