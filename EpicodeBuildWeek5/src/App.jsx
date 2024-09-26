import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import HomePage from "./components/HomePage";
import FormLogin from "./components/FormLogin";
import FattureList from "./components/FattureList"; // Importa il componente per le fatture
import AddFatture from "./components/AddFatture"; // Componente per aggiungere una fattura (da creare)

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<FormLogin />} />
        <Route path="/invoices" element={<FattureList />} />
        <Route path="/invoices/add" element={<AddFatture />} />
      </Routes>
    </Router>
  );
};

export default App;
