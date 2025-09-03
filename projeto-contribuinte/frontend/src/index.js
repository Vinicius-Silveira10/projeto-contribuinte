import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
// 1. Importe o BrowserRouter
import { BrowserRouter } from 'react-router-dom';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        {/* 2. Envolva o <App /> com o <BrowserRouter> */}
        <BrowserRouter>
            <App />
        </BrowserRouter>
    </React.StrictMode>
);
