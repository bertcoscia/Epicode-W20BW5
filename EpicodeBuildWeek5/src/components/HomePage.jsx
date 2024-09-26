import "bootstrap/dist/css/bootstrap.min.css";
import { Link } from "react-router-dom";

const Homepage = () => {
  return (
    <div>
      <section className="head text-white text-center py-5">
        <div className="container">
          <h1 className="display-4 fw-bold">
            Benvenuti a Epic Energy Services SRL
          </h1>
          <p className="lead">
            Soluzioni elettriche innovative per un futuro sostenibile
          </p>
          <Link to="/login" className="btn btn-warning btn-lg mt-3">
            Accedi / Registrati
          </Link>
        </div>
      </section>

      <section className="py-5">
        <div className="container">
          <h2 className="text-center mb-4">Chi Siamo</h2>
          <p className="text-center">
            Epic Energy Services SRL è un azienda leader nel settore dell
            energia elettrica, specializzata in impianti industriali e
            residenziali. Da oltre 20 anni offriamo soluzioni innovative per
            privati, aziende e amministrazioni pubbliche.
          </p>
        </div>
      </section>

      <section className=" py-5">
        <div className="container">
          <h2 className="text-center mb-4">I nostri Servizi</h2>
          <div className="row">
            <div className="col-md-4">
              <div className="card mb-4">
                <div className="card-body">
                  <h3 className="card-title">Impianti Industriali</h3>
                  <p className="card-text">
                    Progettiamo e realizziamo impianti elettrici per industrie
                    di ogni dimensione.
                  </p>
                </div>
              </div>
            </div>
            <div className="col-md-4">
              <div className="card mb-4">
                <div className="card-body">
                  <h3 className="card-title">Impianti Residenziali</h3>
                  <p className="card-text">
                    Installiamo impianti elettrici per abitazioni, garantendo
                    efficienza e sicurezza.
                  </p>
                </div>
              </div>
            </div>
            <div className="col-md-4">
              <div className="card mb-4">
                <div className="card-body">
                  <h3 className="card-title">Manutenzione</h3>
                  <p className="card-text">
                    Offriamo servizi di manutenzione e riparazione per tutti i
                    tipi di impianti.
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      <section className="py-5" id="contatti">
        <div className="container text-center">
          <h2 className="mb-4">Contattaci</h2>
          <p className="mb-4">
            Vuoi ricevere maggiori informazioni? Siamo qui per aiutarti.
          </p>
          <Link to="/email" className="btn bg-warning btn-lg">
            Richiedi un Preventivo
          </Link>
        </div>
      </section>
    </div>
  );
};

export default Homepage;
