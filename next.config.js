module.exports = {
    async redirects() {
      return [
        {
          source: '/',
          destination: '/Glowna',
          permanent: true,
        },
      ];
    },
  };
  
  //Tak bylo domyslnie, zostawiam na wszelki wypadek:

  /** @type {import('next').NextConfig} */
//const nextConfig = {}

//module.exports = nextConfig


  
