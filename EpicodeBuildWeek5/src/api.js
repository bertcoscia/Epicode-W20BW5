const API_URL = "http://localhost:8080/api"; // Cambia la porta se necessario

// Funzione per la registrazione
export const register = async (data) => {
  const response = await fetch(`${API_URL}/register`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });
  if (!response.ok) {
    throw new Error("Errore durante la registrazione");
  }
  return response.json();
};

// Funzione per il login
export const login = async (data) => {
  const response = await fetch(`${API_URL}/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });
  if (!response.ok) {
    throw new Error("Errore durante il login");
  }
  return response.json();
};

// Funzione per ottenere le fatture
export const getInvoices = async () => {
  const response = await fetch(`${API_URL}/invoices`, {
    method: "GET",
    headers: {
      Authorization: `Bearer ${localStorage.getItem("token")}`, // Aggiungere il token
    },
  });
  if (!response.ok) {
    throw new Error("Errore nel recupero delle fatture");
  }
  return response.json();
};

// Funzione per aggiungere una fattura
export const addInvoice = async (invoice) => {
  const response = await fetch(`${API_URL}/invoices`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
    body: JSON.stringify(invoice),
  });
  if (!response.ok) {
    throw new Error("Errore nell'aggiunta della fattura");
  }
  return response.json();
};

// Funzione per modificare una fattura
export const updateInvoice = async (id, invoice) => {
  const response = await fetch(`${API_URL}/invoices/${id}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
    body: JSON.stringify(invoice),
  });
  if (!response.ok) {
    throw new Error("Errore nella modifica della fattura");
  }
  return response.json();
};

// Funzione per eliminare una fattura
export const deleteInvoice = async (id) => {
  const response = await fetch(`${API_URL}/invoices/${id}`, {
    method: "DELETE",
    headers: {
      Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
  });
  if (!response.ok) {
    throw new Error("Errore nell'eliminazione della fattura");
  }
  return response.json();
};
