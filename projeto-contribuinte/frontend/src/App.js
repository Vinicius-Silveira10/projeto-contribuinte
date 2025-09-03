import React, { useState } from 'react';
import { Routes, Route, Link } from 'react-router-dom';
import './App.css';
import Auditoria from './Auditoria';
import Consulta from './Consulta';
import CadastroUsuario from './CadastroUsuario';

function App() {
    const [credentials, setCredentials] = useState(null);

    const handleLogin = (creds) => {
        setCredentials(creds);
    };

    return (
        <div className="App">
            <header className="app-header">
                <nav>
                    {/* A navegação agora usa o componente <Link> */}
                    <Link to="/">Consulta de Contribuinte</Link>
                    <Link to="/auditoria">Painel de Auditoria</Link>
                    <Link to="/cadastro">Cadastrar Usuário</Link>
                </nav>
            </header>
            <main className="content">
                {/* O <Routes> decide qual componente mostrar com base na URL */}
                <Routes>
                    <Route path="/" element={<Consulta />} />
                    <Route path="/cadastro" element={<CadastroUsuario />} />
                    <Route
                        path="/auditoria"
                        element={
                            credentials
                                ? <Auditoria credentials={credentials} />
                                : <Login onLogin={handleLogin} />
                        }
                    />
                </Routes>
            </main>
        </div>
    );
}

// Componente de Login extraído para maior clareza
function Login({ onLogin }) {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = () => {
        // Validação básica para não enviar credenciais vazias
        if (email && password) {
            onLogin({ email, password });
        } else {
            alert('Por favor, preencha o email e a senha.');
        }
    };

    return (
        <div style={{ padding: '20px', textAlign: 'center' }}>
            <h2>Acesso Restrito ao Painel de Auditoria</h2>
            <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Email (admin@example.com)"
                style={{ display: 'block', margin: '10px auto', padding: '8px' }}
            />
            <input
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Senha (Roger123)"
                style={{ display: 'block', margin: '10px auto', padding: '8px' }}
            />
            <button onClick={handleSubmit}>Entrar</button>
        </div>
    );
}

export default App;