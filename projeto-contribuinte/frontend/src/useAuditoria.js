import { useState, useEffect } from 'react';

export function useAuditoria(credentials) {
    const [auditorias, setAuditorias] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (!credentials || !credentials.email) {
            setError("Credenciais não fornecidas.");
            setLoading(false);
            return;
        }

        const fetchAuditoria = async () => {
            setLoading(true); // Reinicia o loading para novas credenciais
            setError(null);

            try {
                const headers = new Headers();
                const authString = `${credentials.email}:${credentials.password}`;
                headers.set('Authorization', 'Basic ' + btoa(authString));

                const response = await fetch(`${process.env.REACT_APP_API_URL}/auditoria`, {
                    method: 'GET',
                    headers: headers,
                });

                if (!response.ok) {
                    if (response.status === 401) {
                        setError('Credenciais inválidas.');
                    } else {
                        setError(`Erro HTTP! status: ${response.status}`);
                    }
                    return; // Sai da função em caso de erro
                }

                const data = await response.json();

                // PONTO DE DIAGNÓSTICO PARA O PRÓXIMO ERRO
                console.log('DADOS REAIS DA API:', data);

                setAuditorias(data);
            } catch (e) {
                setError(e.message);
            } finally {
                setLoading(false);
            }
        };

        fetchAuditoria().catch(console.error);

    }, [credentials]);

    return { auditorias, loading, error };
}