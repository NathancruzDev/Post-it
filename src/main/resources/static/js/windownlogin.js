//TODO:criar uma  funcao para abrir uma micro janela na apgina de login e estar diversificada entre cadastro e login. ou seja uma janela para cada

function windowLogin(modalId){
    document.getElementById(modalId).style.display='block'
}

function closeWindowLogin(modalId){
    document.getElementById(modalId).style.display='none'
}

  window.onclick = function(event) {
            if (event.target.classList.contains('modal')) {
                event.target.style.display = 'none';
            }
        }