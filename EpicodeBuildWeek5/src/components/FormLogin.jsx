import { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";

const AuthForm = () => {
  const [isLogin, setIsLogin] = useState(true);
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: "",
    nome: "",
    cognome: "",
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");
    setSuccess("");

    const endpoint = isLogin ? "ENDPOINT_LOGIN" : "ENDPOINT_REGISTRAZIONE";

    try {
      const response = await fetch(endpoint, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      const data = await response.json();

      if (!response.ok) {
        throw new Error(data.message || "Errore durante l'autenticazione");
      }

      setSuccess(
        isLogin
          ? "Login effettuato con successo!"
          : "Registrazione avvenuta con successo!"
      );
      setFormData({
        username: "",
        email: "",
        password: "",
        nome: "",
        cognome: "",
      });
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div
      className="d-flex justify-content-center align-items-center vh-100"
      style={{
        backgroundColor: "#4B0082",
        backgroundSize: "cover",
        backgroundPosition: "center",
      }}
    >
      <div className="card p-4" style={{ minWidth: "400px" }}>
        <h2 className="text-center">{isLogin ? "Login" : "Registrazione"}</h2>

        {error && <div className="alert alert-danger">{error}</div>}
        {success && <div className="alert alert-success">{success}</div>}

        <form onSubmit={handleSubmit}>
          {!isLogin && (
            <>
              <div className="mb-3">
                <label htmlFor="nome" className="form-label">
                  Nome
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="nome"
                  name="nome"
                  value={formData.nome}
                  onChange={handleChange}
                  required
                />
              </div>
              <div className="mb-3">
                <label htmlFor="cognome" className="form-label">
                  Cognome
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="cognome"
                  name="cognome"
                  value={formData.cognome}
                  onChange={handleChange}
                  required
                />
              </div>
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
            </>
          )}

          {/* Username, Email e Password richiesti anche nel Login */}
          {isLogin && (
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

          <button
            type="submit"
            className="btn btn-primary w-100"
            disabled={loading}
          >
            {loading ? "Caricamento..." : isLogin ? "Accedi" : "Registrati"}
          </button>
        </form>

        <div className="mt-3 text-center">
          <button
            className="btn btn-link"
            onClick={() => setIsLogin((prev) => !prev)}
            disabled={loading}
          >
            {isLogin
              ? "Non hai un account? Registrati"
              : "Hai già un account? Accedi"}
          </button>
        </div>
      </div>
    </div>
  );
};

export default AuthForm;
