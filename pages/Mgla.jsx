
import { motion } from 'framer-motion';

const Mgla = () => {
  return (
    <motion.div
      initial={{ opacity: 0 }}
      animate={{ opacity: 0.2 }}
      exit={{ opacity: 0 }}
      transition={{ duration: 2 }}
      style={{
        top: 0,
        position: 'absolute',
        top: '50%', // Ustawienie górnej krawędzi tęczy na środku ekranu
        left: 0,
        width: '100%',
        height: '5%', // Ustawienie wysokości paska na 10% ekranu
        background: 'linear-gradient(to right, rgba(255, 0, 0, 0.2), rgba(255, 153, 0, 0.2), rgba(255, 255, 0, 0.2), rgba(0, 255, 0, 0.2), rgba(0, 0, 255, 0.2), rgba(153, 0, 255, 0.2), rgba(255, 0, 255, 0.2))',
        transform: 'translateY(-210px)', // Przesunięcie o połowę wysokości
        zIndex: 999,
        borderBottom: '3px dashed rgba(255, 255, 255, 0.2)',
        borderTop: '3px dashed rgba(128, 128, 128, 0.2)',

      }}
    />
  );
};

export default Mgla;
