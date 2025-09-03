import React from 'react';
import './Auditoria.css';
import { useAuditoria } from './useAuditoria'; // Importa o nosso novo hook

function Auditoria({ credentials }) {
    const { auditorias, loading, error } = useAuditoria(credentials);

    if (loading) {
        return <div>Carregando dados...</div>;
    }

    if (error) {
        return <div>Erro ao carregar os dados: {error}</div>;
    }

    return (
        <div className="auditoria-container">
            <h2>Painel de Auditoria</h2>
            <table className="auditoria-table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>CPF Consultado</th>
                    <th>IP do Solicitante</th>
                    <th>Data e Hora</th>
                </tr>
                </thead>
                <tbody>
                {auditorias.map((log) => (
                    <tr key={log.id}>
                        <td>{log.id}</td>
                        <td>{log.cpfConsultado}</td>
                        <td>{log.ip}</td>
                        <td>{new Date(log.timestamp).toLocaleString('pt-BR')}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default Auditoria;