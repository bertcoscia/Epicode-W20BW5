import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import HomePage from "./components/HomePage";
import FormLogin from "./components/FormLogin";
import EmailForm from "./components/EmailForm";

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<FormLogin />} />
        <Route path="/email" element={<EmailForm />} />
      </Routes>
    </Router>
  );
};

export default App;
