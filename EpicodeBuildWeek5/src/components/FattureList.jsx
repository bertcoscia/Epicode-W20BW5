import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  setInvoices,
  deleteInvoice as deleteInvoiceAction,
} from "../Store/invoiceSlice";
import { getInvoices, deleteInvoice } from "../api";

const InvoiceList = () => {
  const dispatch = useDispatch();
  const invoices = useSelector((state) => state.invoices.list);

  useEffect(() => {
    const fetchInvoices = async () => {
      try {
        const data = await getInvoices();
        dispatch(setInvoices(data));
      } catch (error) {
        console.error("Error fetching invoices:", error);
      }
    };

    fetchInvoices();
  }, [dispatch]);

  const handleDelete = async (id) => {
    try {
      await deleteInvoice(id);
      dispatch(deleteInvoiceAction(id));
    } catch (error) {
      console.error("Error deleting invoice:", error);
    }
  };

  return (
    <div>
      <h2>Fatture</h2>
      <ul>
        {invoices.map((invoice) => (
          <li key={invoice.id}>
            {invoice.numero} - {invoice.importo} - {invoice.data} -{" "}
            {invoice.stato}
            <button onClick={() => handleDelete(invoice.id)}>Elimina</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default InvoiceList;
