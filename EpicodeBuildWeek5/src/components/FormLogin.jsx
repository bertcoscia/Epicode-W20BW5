import { useState } from "react";

const AuthForm = () => {
  const [isLogin, setIsLogin] = useState(true);
  const [formData, setFormData] = useState({
    email: "",
    password: "",
    username: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(formData);
    setFormData({ email: "", password: "", username: "" });
  };

  return (
    <div className="container mt-5">
      <h2 className="text-center">{isLogin ? "Login" : "Registrazione"}</h2>
      <form onSubmit={handleSubmit}>
        {!isLogin && (
          <div className="mb-3">
            <label htmlFor="username" className="form-label">
              Nome Utente
            </label>
            <input
              type="text"
              className="form-control"
              id="username"
              name="username"
              value={formData.username}
              onChange={handleChange}
              required
            />
          </div>
        )}
        <div className="mb-3">
          <label htmlFor="email" className="form-label">
            Email
          </label>
          <input
            type="email"
            className="form-control"
            id="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="password" className="form-label">
            Password
          </label>
          <input
            type="password"
            className="form-control"
            id="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">
          {isLogin ? "Accedi" : "Registrati"}
        </button>
      </form>
      <div className="mt-3 text-center">
        <button
          className="btn btn-link"
          onClick={() => setIsLogin((prev) => !prev)}
        >
          {isLogin
            ? "Non hai un account? Registrati"
            : "Hai già un account? Accedi"}
        </button>
      </div>
    </div>
  );
};

export default AuthForm;
