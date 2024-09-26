import { createSlice } from "@reduxjs/toolkit";

const invoiceSlice = createSlice({
  name: "invoices",
  initialState: {
    list: [],
  },
  reducers: {
    setInvoices(state, action) {
      state.list = action.payload;
    },
    addInvoice(state, action) {
      state.list.push(action.payload);
    },
    updateInvoice(state, action) {
      const index = state.list.findIndex(
        (invoice) => invoice.id === action.payload.id
      );
      if (index !== -1) {
        state.list[index] = action.payload;
      }
    },
    deleteInvoice(state, action) {
      state.list = state.list.filter(
        (invoice) => invoice.id !== action.payload
      );
    },
  },
});

export const { setInvoices, addInvoice, updateInvoice, deleteInvoice } =
  invoiceSlice.actions;
export default invoiceSlice.reducer;
