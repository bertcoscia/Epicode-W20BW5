import { useState } from "react";
import { useDispatch } from "react-redux";
import { addInvoice } from "../api";
import { addInvoice as addInvoiceAction } from "../Store/invoiceSlice";

const AddFatture = () => {
  const [formData, setFormData] = useState({
    numero: "",
    importo: "",
    data: "",
    stato: "",
  });
  const dispatch = useDispatch();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const newInvoice = await addInvoice(formData);
      dispatch(addInvoiceAction(newInvoice)); // Aggiungi la fattura allo stato
      // Resetta il modulo
      setFormData({ numero: "", importo: "", data: "", stato: "" });
    } catch (error) {
      console.error("Error adding invoice:", error);
    }
  };

  return (
    <div>
      <h2>Aggiungi Fattura</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="numero">Numero Fattura</label>
          <input
            type="text"
            id="numero"
            name="numero"
            value={formData.numero}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="importo">Importo</label>
          <input
            type="number"
            id="importo"
            name="importo"
            value={formData.importo}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="data">Data</label>
          <input
            type="date"
            id="data"
            name="data"
            value={formData.data}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label htmlFor="stato">Stato</label>
          <input
            type="text"
            id="stato"
            name="stato"
            value={formData.stato}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Aggiungi Fattura</button>
      </form>
    </div>
  );
};

export default AddFatture;
