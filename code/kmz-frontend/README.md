# KMZ Frontend

**Digitalization and Valorization Platform for the Local Agricultural Supply Chain**  
Frontent developed in **Next js**.

## Requirements

- **Node.js 20+**
- **npm 10+** (recommended)
- Backend API running on `http://localhost:8080` (or configured environment base URL)
- GitHub Personal Access Token (for accessing private npm packages)

> Before running the frontend, make sure the backend application is running.


## Setup

### 1. Configure access to GitHub Packages

Create a file named **`.npmrc`** in the project root (same level as `package.json`) and add:

```bash
@edoandcode:registry=https://npm.pkg.github.com
//npm.pkg.github.com/:_authToken=COPY_PERSONAL_ACCESS_TOKEN_HERE
```

Replace COPY_PERSONAL_ACCESS_TOKEN_HERE with your GitHub Personal Access Token that has read:packages permission.

This is required to install the private UI Kit library:

```bash
@edoandcode/ui-kit-grid
```

⚠️ Never commit ``.npmrc`` files containing personal tokens to version control.


## Running the application

1. Install dependencies
    ```bash
    npm install
    ```

2. Build for production

    ```bash
    npm run build
    ```

3. Start the production server
    ```bash
    npm start
    ```

4. Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.


## Project Structure
```graphql
kmz-frontend/
├── src/
│   ├── app/                     # App Router (Next.js 16+)
│   │   ├── layout.tsx           # Root layout and shared UI
│   │   ├── page.tsx             # Homepage or entry point
│   │   └── ...                  # Other routes and nested layouts
│   ├── components/              # Reusable UI components
│   ├── hooks/                   # Custom React hooks
│   ├── services/                # API and utility modules
│   ├── settings/                # Configurations (API endpoints, routes, constants)
│   ├── types/                   # TypeScript types definition
│   └── styles/                  # Global and Tailwind styles
├── public/                      # Static assets
├── tsconfig.json                # TypeScript configuration
├── package.json
├── .npmrc                       # GitHub Packages auth (private UI Kit)
├── ui-kit-grid-config.mjs       # Configuration file for package @edoandcode/uikit-grid
├── ui-kit-tailwind-safelist     # Auto-generated file by package @edoandcode/uikit-grid
└── README.md
```

## Technologies Used

### Frameworks and Languages
- **Next.js 16** – App Router, Server Actions, and hybrid SSR/ISR rendering.  
- **React 19** – Reactive and modular user interface library.  
- **TypeScript 5** – Static typing for more robust and maintainable code.  

### UI and Styling
- **Tailwind CSS 4** – Utility-first CSS framework powered by PostCSS.  
- **@edoandcode/ui-kit-grid** – Custom library for responsive grid layouts.  
- **Radix UI** – Accessible, unstyled UI components.  
- **Lucide React** – Modern vector icons for React.  

### State Management and Validation
- **React Hook Form + Zod + @hookform/resolvers** – Typed form handling and schema-based validation.  
- **SWR** – Reactive data fetching with caching and revalidation.  
- **Sonner** – Elegant and minimal toast notification system.  

### Authentication and Security
- **NextAuth.js v5** – Client/server authentication integrated with the Spring Boot backend.  
- **jwt-decode** – Client-side decoding of JWT tokens.  

### Utilities and Support
- **Lodash** – General-purpose utility functions.  
- **OpenLayers (ol)** – Interactive mapping and geospatial visualization.  

### Development Tools
- **ESLint 9 + eslint-config-next** – Linting setup aligned with Next.js standards.  
- **TypeScript** – Static type checking for safer, more reliable development.  
- **Turbopack** – Next-generation bundler for high-performance builds and dev server.


