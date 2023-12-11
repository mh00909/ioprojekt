module.exports = {
    async redirects() {
      return [
        {
          source: '/',
          destination: '/Main',
          permanent: true,
        },
      ];
    },
  };
  
  //Tak bylo domyslnie, zostawiam na wszelki wypadek:

  /** @type {import('next').NextConfig} */
//const nextConfig = {}

//module.exports = nextConfig


  
