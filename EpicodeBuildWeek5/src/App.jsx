import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import HomePage from "./components/HomePage";
import FormLogin from "./components/FormLogin";

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<FormLogin />} />
      </Routes>
    </Router>
  );
};

export default App;
