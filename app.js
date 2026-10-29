let areasDisponiveis = window.DADOS_AREAS || [];
let areasSelecionadas = new Set();
let temaAtual = null;
let ultimoTemaSorteadoId = null;
let historico = carregarHistorico();
let servidorAtivo = false;

const areasGrid = document.getElementById("areasGrid");
const btnSortear = document.getElementById("btnSortear");
const actionHint = document.getElementById("actionHint");
const resultCard = document.getElementById("resultCard");
const resultContent = document.getElementById("resultContent");
const resultAreaTag = document.getElementById("resultAreaTag");
const resultLevel = document.getElementById("resultLevel");
const resultTitle = document.getElementById("resultTitle");
const resultDescription = document.getElementById("resultDescription");
const resultKeywords = document.getElementById("resultKeywords");
const btnIniciar = document.getElementById("btnIniciar");
const sectionHistory = document.getElementById("sectionHistory");
const historyList = document.getElementById("historyList");
const historyCount = document.getElementById("historyCount");
const btnClearHistory = document.getElementById("btnClearHistory");
const helpBtn = document.getElementById("helpBtn");
const helpModal = document.getElementById("helpModal");
const helpModalClose = document.getElementById("helpModalClose");

async function carregarTemasJson() {
  if (window.DADOS_AREAS && Array.isArray(window.DADOS_AREAS) && window.DADOS_AREAS.length > 0) {
    areasDisponiveis = window.DADOS_AREAS;
    return true;
  }

  try {
    const res = await fetch("temas.json", { cache: "no-store" });
    if (res.ok) {
      const dados = await res.json();
      if (Array.isArray(dados) && dados.length > 0) {
        areasDisponiveis = dados;
        return true;
      }
    }
  } catch (e) {
  }
  return false;
}

document.addEventListener("DOMContentLoaded", async () => {
  await carregarTemasJson();
  await verificarConexaoServidor();
  renderizarAreas();
  atualizarHistoricoUI();
  configurarEventos();

  carregarTemaInicial();
});

function obterPoolTemas() {
  const pool = [];
  const usarTodas = areasSelecionadas.size === 0 || areasSelecionadas.size === areasDisponiveis.length;

  areasDisponiveis.forEach(area => {
    if (usarTodas || areasSelecionadas.has(area.id)) {
      if (area.temas && Array.isArray(area.temas)) {
        area.temas.forEach(tema => {
          pool.push({
            ...tema,
            areaId: area.id,
            areaNome: area.nome
          });
        });
      }
    }
  });

  return pool;
}

function carregarTemaInicial() {
  const pool = obterPoolTemas();
  if (pool.length > 0) {
    revelarTemaFinal(pool[0], false);
  }
}

async function verificarConexaoServidor() {
  try {
    const res = await fetch("/api/areas", { cache: "no-store" });
    if (res.ok) {
      const dados = await res.json();
      if (Array.isArray(dados) && dados.length > 0) {
        servidorAtivo = true;
        return;
      }
    }
  } catch (e) {
  }

  servidorAtivo = false;
}

function renderizarAreas() {
  areasGrid.innerHTML = "";

  areasDisponiveis.forEach(area => {
    const card = document.createElement("div");
    card.className = `area-card ${areasSelecionadas.has(area.id) ? "active" : ""}`;
    card.dataset.id = area.id;

    card.innerHTML = `<span class="area-name">${area.nome}</span>`;

    card.addEventListener("click", () => alternarSelecaoArea(area, card));
    areasGrid.appendChild(card);
  });

  atualizarTextoDica();
}

function alternarSelecaoArea(area, cardElement) {
  if (areasSelecionadas.has(area.id)) {
    areasSelecionadas.delete(area.id);
    cardElement.classList.remove("active");
    tocarSom(280, 0.03);
  } else {
    areasSelecionadas.add(area.id);
    cardElement.classList.add("active");
    tocarSom(340, 0.04);
  }

  atualizarTextoDica();
}

function atualizarTextoDica() {
  if (!actionHint) return;
  const total = areasDisponiveis.length;
  const qtd = areasSelecionadas.size;

  if (qtd === 0) {
    actionHint.innerHTML = `Buscando em: <strong>Todas as áreas</strong> (nenhuma restrição)`;
  } else if (qtd === 1) {
    const id = Array.from(areasSelecionadas)[0];
    const area = areasDisponiveis.find(a => a.id === id);
    const nome = area ? area.nome : id;
    actionHint.innerHTML = `Área selecionada: <strong>${nome}</strong>`;
  } else if (qtd === total) {
    actionHint.innerHTML = `Buscando em: <strong>Todas as áreas</strong> (${total} áreas ativas)`;
  } else {
    const nomes = Array.from(areasSelecionadas)
      .map(id => {
        const a = areasDisponiveis.find(x => x.id === id);
        return a ? a.nome : id;
      });
    if (qtd <= 2) {
      actionHint.innerHTML = `Áreas selecionadas (${qtd}): <strong>${nomes.join(" e ")}</strong>`;
    } else {
      actionHint.innerHTML = `Áreas selecionadas (${qtd}): <strong>${nomes.slice(0, 2).join(", ")} e mais ${qtd - 2}</strong>`;
    }
  }
}

async function executarSorteio() {
  if (btnSortear.disabled) return;
  btnSortear.disabled = true;

  const pool = obterPoolTemas();
  if (pool.length === 0) {
    btnSortear.disabled = false;
    return;
  }

  if (resultDescription) resultDescription.textContent = "Selecionando tópico de estudo...";
  resultKeywords.innerHTML = "";

  let temaEscolhido;
  if (servidorAtivo) {
    try {
      const param = areasSelecionadas.size > 0 ? `?areas=${encodeURIComponent(Array.from(areasSelecionadas).join(","))}` : "";
      const res = await fetch(`/api/sortear${param}`);
      if (res.ok) {
        const dados = await res.json();
        temaEscolhido = dados.tema;
        if (dados.area) {
          temaEscolhido.areaNome = dados.area.nome;
          temaEscolhido.areaId = dados.area.id;
        }
      }
    } catch (e) {
      console.warn("Falha no servidor Java, chaveando para sorteador local:", e);
    }
  }

  if (!temaEscolhido) {
    temaEscolhido = sortearLocalmente();
  }

  let animCount = 0;
  const maxAnim = 8;
  const intervalo = setInterval(() => {
    const randomTemp = pool[Math.floor(Math.random() * pool.length)];
    resultAreaTag.textContent = (randomTemp.areaNome || "COMPUTAÇÃO").toUpperCase();
    resultTitle.textContent = randomTemp.titulo;
    tocarSom(480 + animCount * 25, 0.03);
    animCount++;

    if (animCount >= maxAnim) {
      clearInterval(intervalo);
      revelarTemaFinal(temaEscolhido, true);
    }
  }, 60);
}

function sortearLocalmente() {
  const pool = obterPoolTemas();
  if (pool.length === 0) return null;
  if (pool.length === 1) return pool[0];

  let selecionado;
  let tentativas = 0;
  do {
    const idx = Math.floor(Math.random() * pool.length);
    selecionado = pool[idx];
    tentativas++;
  } while (selecionado.id === ultimoTemaSorteadoId && tentativas < 25);

  ultimoTemaSorteadoId = selecionado.id;
  return selecionado;
}

function revelarTemaFinal(tema, comEfeitos) {
  temaAtual = tema;
  btnSortear.disabled = false;
  resultCard.classList.add("revealed");

  resultAreaTag.textContent = (tema.areaNome || "COMPUTAÇÃO").toUpperCase();
  resultTitle.textContent = tema.titulo;
  if (resultDescription) resultDescription.textContent = tema.descricao;

  resultKeywords.innerHTML = "";
  const topicsAccordion = document.getElementById("topicsAccordion");
  if (topicsAccordion) topicsAccordion.open = false;

  if (tema.topicosChave && Array.isArray(tema.topicosChave)) {
    tema.topicosChave.forEach(topico => {
      const item = document.createElement("li");
      item.className = "topic-item";
      item.textContent = topico;
      resultKeywords.appendChild(item);
    });
  }

  if (comEfeitos) {
    tocarAcordeSucesso();
    dispararConfetesRosa();
    adicionarAoHistorico({ nome: tema.areaNome, id: tema.areaId }, tema);
  }
}

function formatarDataHistorico(d = new Date()) {
  const dia = d.toLocaleDateString("pt-BR", { day: "2-digit", month: "2-digit" });
  const hora = d.toLocaleTimeString("pt-BR", { hour: "2-digit", minute: "2-digit" });
  return `${dia} às ${hora}`;
}

function adicionarAoHistorico(area, tema) {
  historico.unshift({
    areaNome: area.nome,
    temaTitulo: tema.titulo,
    temaNivel: tema.nivel,
    data: formatarDataHistorico(new Date())
  });

  if (historico.length > 15) {
    historico.pop();
  }

  localStorage.setItem("devstudy_history", JSON.stringify(historico));
  atualizarHistoricoUI();
}

function carregarHistorico() {
  try {
    const salvo = localStorage.getItem("devstudy_history");
    return salvo ? JSON.parse(salvo) : [];
  } catch (e) {
    return [];
  }
}

function atualizarHistoricoUI() {
  if (!historico || historico.length === 0) {
    sectionHistory.style.display = "none";
    if (sectionHistory.open) sectionHistory.open = false;
    return;
  }

  sectionHistory.style.display = "block";
  historyCount.textContent = historico.length;
  historyList.innerHTML = "";

  historico.forEach(item => {
    const div = document.createElement("div");
    div.className = "history-item";

    const areaTexto = item.areaNome ? item.areaNome.toUpperCase() : "COMPUTAÇÃO";

    div.innerHTML = `
      <div class="history-item-left">
        <span class="history-item-area">${areaTexto}</span>
        <strong class="history-item-title">${item.temaTitulo}</strong>
      </div>
      <span class="history-item-time">${item.data}</span>
    `;
    historyList.appendChild(div);
  });
}

function limparHistorico() {
  historico = [];
  localStorage.removeItem("devstudy_history");
  if (sectionHistory) sectionHistory.open = false;
  atualizarHistoricoUI();
}

function configurarEventos() {
  btnSortear.addEventListener("click", executarSorteio);

  if (btnIniciar) {
    btnIniciar.addEventListener("click", () => {
      if (!temaAtual) return;
      const t = encodeURIComponent(temaAtual.titulo);
      const a = encodeURIComponent(temaAtual.areaNome || "");
      window.open(`timer.html?tema=${t}&area=${a}`, "_blank");
    });
  }
  btnClearHistory.addEventListener("click", limparHistorico);

  if (helpBtn && helpModal) {
    helpBtn.addEventListener("click", () => {
      helpModal.classList.add("active");
    });
  }
  if (helpModalClose && helpModal) {
    helpModalClose.addEventListener("click", () => {
      helpModal.classList.remove("active");
    });
  }
  if (helpModal) {
    helpModal.addEventListener("click", (e) => {
      if (e.target === helpModal) {
        helpModal.classList.remove("active");
      }
    });
    document.addEventListener("keydown", (e) => {
      if (e.key === "Escape" && helpModal.classList.contains("active")) {
        helpModal.classList.remove("active");
      }
    });
  }
}

let audioCtx = null;
function getAudioContext() {
  if (!audioCtx) {
    const AudioContextClass = window.AudioContext || window.webkitAudioContext;
    if (AudioContextClass) audioCtx = new AudioContextClass();
  }
  return audioCtx;
}

function tocarSom(frequencia, duracao) {
  try {
    const ctx = getAudioContext();
    if (!ctx) return;
    const osc = ctx.createOscillator();
    const gain = ctx.createGain();
    osc.type = "sine";
    osc.frequency.setValueAtTime(frequencia, ctx.currentTime);
    gain.gain.setValueAtTime(0.06, ctx.currentTime);
    gain.gain.exponentialRampToValueAtTime(0.001, ctx.currentTime + duracao);
    osc.connect(gain);
    gain.connect(ctx.destination);
    osc.start();
    osc.stop(ctx.currentTime + duracao);
  } catch (e) {}
}

function tocarAcordeSucesso() {
  tocarSom(523.25, 0.2);
  setTimeout(() => tocarSom(659.25, 0.25), 90);
  setTimeout(() => tocarSom(783.99, 0.35), 180);
}

function dispararConfetesRosa() {
  const canvas = document.getElementById("confettiCanvas");
  if (!canvas) return;
  const ctx = canvas.getContext("2d");
  canvas.width = window.innerWidth;
  canvas.height = window.innerHeight;

  const particulas = [];
  const cores = ["#6C0820", "#F2AEBC", "#F2DCDB", "#5A86CB", "#3D5D91", "#ffffff"];

  for (let i = 0; i < 55; i++) {
    particulas.push({
      x: canvas.width / 2,
      y: canvas.height / 2 + 30,
      vx: (Math.random() - 0.5) * 11,
      vy: (Math.random() - 0.8) * 13,
      size: Math.random() * 8 + 4,
      cor: cores[Math.floor(Math.random() * cores.length)],
      alpha: 1,
      rotacao: Math.random() * 360,
      vRotacao: (Math.random() - 0.5) * 8
    });
  }

  let animFrame;
  function animar() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    let vivas = 0;

    particulas.forEach(p => {
      p.x += p.vx;
      p.y += p.vy;
      p.vy += 0.32;
      p.alpha -= 0.016;
      p.rotacao += p.vRotacao;

      if (p.alpha > 0) {
        vivas++;
        ctx.save();
        ctx.globalAlpha = p.alpha;
        ctx.translate(p.x, p.y);
        ctx.rotate((p.rotacao * Math.PI) / 180);
        ctx.fillStyle = p.cor;
        ctx.fillRect(-p.size / 2, -p.size / 2, p.size, p.size);
        ctx.restore();
      }
    });

    if (vivas > 0) {
      animFrame = requestAnimationFrame(animar);
    } else {
      ctx.clearRect(0, 0, canvas.width, canvas.height);
      cancelAnimationFrame(animFrame);
    }
  }

  animar();
}
