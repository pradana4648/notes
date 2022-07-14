/** @type {import('tailwindcss').Config} */
module.exports = {
  // content: ["./src/main/resources/templates/**/*.{html,js}"],
  mode: process.env.NODE_ENV ? "jit" : undefined,
  content: ["./src/**/*.{html,js}"],
  theme: {
    extend: {},
  },
  plugins: [],
};
