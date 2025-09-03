import React, { useState } from 'react';
import './Consulta.css';

process.env.REACT_APP_API_URL = undefined;

function Consulta() {
    const [cpf, setCpf] = useState('');
    // Vamos renomear o estado para refletir que recebemos o DTO completo
    const [consultaResult, setConsultaResult] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const handleSearch = async () => {
        setLoading(true);
        setError(null);
        setConsultaResult(null); // Limpa o resultado anterior

        try {
            const response = await fetch(`${process.env.REACT_APP_API_URL}/consulta/cpf?cpf=${cpf}`);
            if (!response.ok) {
                const errorBody = await response.text(); // Pega o corpo do erro do backend
                throw new Error(errorBody || `Erro HTTP! status: ${response.status}`);
            }
            const data = await response.json();

            // Lógica de verificação aprimorada
            if (!data.contribuinte && !data.dadosExternos) {
                setError('CPF não encontrado em nenhuma das fontes de dados.');
            } else {
                setConsultaResult(data); // Salva o resultado completo
            }

        } catch (e) {
            setError(e.message || 'Erro ao buscar dados do contribuinte.');
            console.error(e);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="consulta-container">
            <h2>Consulta de Contribuinte</h2>
            <div className="search-box">
                <input
                    type="text"
                    value={cpf}
                    onChange={(e) => setCpf(e.target.value)}
                    placeholder="Digite o CPF/CNPJ"
                />
                <button onClick={handleSearch} disabled={loading}>
                    {loading ? 'Consultando...' : 'Consultar'}
                </button>
            </div>

            {loading && <div className="loading">Carregando dados...</div>}
            {error && <div className="error">{error}</div>}

            {/* Nova lógica de renderização */}
            {consultaResult && (
                <div className="results-container">
                    {consultaResult.contribuinte && (
                        <div className="contribuinte-info">
                            <h3>Dados do Contribuinte (Banco Local)</h3>
                            <p><strong>Nome:</strong> {consultaResult.contribuinte.nome}</p>
                            <p><strong>CPF:</strong> {consultaResult.contribuinte.cpf}</p>
                            <p><strong>Endereço:</strong> {consultaResult.contribuinte.endereco}</p>
                            <p><strong>Email:</strong> {consultaResult.contribuinte.email}</p>
                        </div>
                    )}

                    {consultaResult.dadosExternos && (
                        <div className="contribuinte-info">
                            <h3>Dados de Dívidas (API Externa)</h3>
                            <p><strong>Contribuinte:</strong> {consultaResult.dadosExternos.contribuinte}</p>
                            <p><strong>CPF/CNPJ:</strong> {consultaResult.dadosExternos.cpf_cnpj}</p>
                            <p><strong>Tributo:</strong> {consultaResult.dadosExternos.tributo}</p>
                            <p><strong>Valor Original:</strong> {consultaResult.dadosExternos.valor_original}</p>
                            <p><strong>Status:</strong> {consultaResult.dadosExternos.status}</p>
                        </div>
                    )}
                </div>
            )}
        </div>
    );
}

export default Consulta;