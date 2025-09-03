import React, { useState } from 'react';
import { criarNovoUsuario } from './services/api'; // Importa a função da API

function CadastroUsuario() {
    const [nome, setNome] = useState('');
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');
    const [mensagem, setMensagem] = useState('');
    const [erro, setErro] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault(); // Previne o recarregamento da página
        setMensagem('');
        setErro('');

        if (!nome || !email || !senha) {
            setErro('Todos os campos são obrigatórios.');
            return;
        }

        try {
            const novoUsuario = { nome, email, senha };
            await criarNovoUsuario(novoUsuario);
            setMensagem(`Usuário "${nome}" criado com sucesso!`);
            // Limpa o formulário
            setNome('');
            setEmail('');
            setSenha('');
        } catch (error) {
            setErro(error.message);
        }
    };

    return (
        <div style={{ padding: '20px', maxWidth: '400px', margin: 'auto' }}>
            <h2>Cadastro de Novo Usuário</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    value={nome}
                    onChange={(e) => setNome(e.target.value)}
                    placeholder="Nome Completo"
                    style={{ width: '100%', padding: '8px', marginBottom: '10px' }}
                />
                <input
                    type="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder="Email"
                    style={{ width: '100%', padding: '8px', marginBottom: '10px' }}
                />
                <input
                    type="password"
                    value={senha}
                    onChange={(e) => setSenha(e.target.value)}
                    placeholder="Senha"
                    style={{ width: '100%', padding: '8px', marginBottom: '10px' }}
                />
                <button type="submit" style={{ width: '100%', padding: '10px' }}>
                    Cadastrar
                </button>
            </form>
            {mensagem && <p style={{ color: 'green', marginTop: '10px' }}>{mensagem}</p>}
            {erro && <p style={{ color: 'red', marginTop: '10px' }}>{erro}</p>}
        </div>
    );
}

export default CadastroUsuario;