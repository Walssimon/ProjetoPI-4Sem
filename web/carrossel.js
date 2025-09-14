let currentIndex = 0;

function showSlide(index) {
  const carousel = document.querySelector(".carousel");
  const totalSlides = carousel.children.length;

  // Garantir que o índice não ultrapasse os limites
  if (index < 0) {
    currentIndex = totalSlides - 1;
  } else if (index >= totalSlides) {
    currentIndex = 0;
  } else {
    currentIndex = index;
  }

  const offset = -currentIndex * 100; // Mover a largura da tela
  carousel.style.transform = `translateX(${offset}%)`;
}

// Função para passar para o próximo slide
function nextSlide() {
  showSlide(currentIndex + 1);
}

// Iniciar no primeiro slide
showSlide(currentIndex);

// Passar automaticamente para o próximo slide a cada 3 segundos (3000ms)
setInterval(nextSlide, 3000);
