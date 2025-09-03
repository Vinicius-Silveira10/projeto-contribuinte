const API_URL = process.env.REACT_APP_API_URL;

/**
 * Cria um novo usuário administrador.
 * @param {object} usuario - O objeto do usuário contendo nome, email e senha.
 * @returns {Promise<object>} O usuário criado.
 */
export const criarNovoUsuario = async (usuario) => {
    const response = await fetch(`${API_URL}/usuarios`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(usuario),
    });

    if (!response.ok) {
        // Tenta pegar a mensagem de erro do backend para ser mais específico
        const errorData = await response.text();
        throw new Error(errorData || 'Erro ao criar usuário');
    }

    return response.json();
};

// No futuro, você pode adicionar outras chamadas de API aqui
// ex: export const login = async (credentials) => { ... };