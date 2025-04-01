interface Route {
  title: string;
  path: string;
  icon?: string;
  children?: Route[];
}

export const MENU_ITEMS: Route[] = [
  // Dashboard General
  {
    title: 'Dashboard',
    path: '/Modulo-Galpon',
    icon: 'chart-bar',
  },

  // Módulo Galpón
  {
    title: 'Modulo Galpón',
    path: '/Modulo-Galpon',
    children: [
      {
        title: 'Maestros',
        path: '/Modulo-Galpon/Masters',
        children: [
          { title: 'Tema', path: '/Modulo-Galpon/Tema' },
          { title: 'Proveedor', path: '/Modulo-Galpon/Proveedor' },
          { title: 'Ubicaciones', path: '/Modulo-Galpon/Ubicaciones' },
          { title: 'Tipo de Proveedores', path: '/Modulo-Galpon/Tipo-Proveedores' },
          { title: 'Galpón', path: '/Modulo-Galpon/Galpon' },
          { title: 'Productos', path: '/Modulo-Galpon/Productos' },
        ],
      },
      {
        title: 'Transaccionales',
        path: '/Modulo-Galpon/',
        children: [
          { title: 'Kardex de huevo', path: '/Modulo-Galpon/Kardex de huevo' },
          { title: 'Ventas', path: '/Modulo-Galpon/Ventas' },

        ],
      },
    ],
  },

  // Módulo Bienestar Común
  {
    title: 'Modulo Bienestar Común',
    path: '/Modulo-Bienestar-Comun',
    children: [
      { title: 'Dashboard', path: '/Modulo-Bienestar-Comun/Dashboard' },
      { title: 'Masters', path: '/Modulo-Bienestar-Comun/Masters' },
    ],
  },

  // Módulo Psicología
  {
    title: 'Modulo Psicología',
    path: '/Modulo-Psicologia',
    children: [
      { title: 'Dashboard', path: '/Modulo-Psicologia/Dashboard' },
      { title: 'Masters', path: '/Modulo-Psicologia/Masters' },
    ],
  },
];
