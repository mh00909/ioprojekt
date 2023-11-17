module.exports = {
    testEnvironment: 'jsdom',
    setupFilesAfterEnv: ['<rootDir>/jest/setup.js'],
    testMatch: ['C:/Users/Ania/projektio/testy/**/logowanieTest.jsx'],
    transform: {
        '^.+\\.js$': 'babel-jest',
      },
};
  