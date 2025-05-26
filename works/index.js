const express = require("express");
const { PrismaClient } = require("@prisma/client");

const prisma = new PrismaClient();
const app = express();

app.use(express.json());

// ➤ Récupérer tous les utilisateurs
app.get("/users", async (req, res) => {
  const users = await prisma.user.findMany();
  res.json(users);
});

// ➤ Créer un nouvel utilisateur
app.post("/users", async (req, res) => {
  const { name, email } = req.body;
  try {
    const user = await prisma.user.create({
      data: { name, email },
    });
    res.json(user);
  } catch (error) {
    res.status(400).json({ error: "Email déjà utilisé ou données invalides." });
  }
});

// ➤ Récupérer un utilisateur par ID
app.get("/users/:id", async (req, res) => {
  const user = await prisma.user.findUnique({
    where: { id: parseInt(req.params.id) },
  });
  if (user) {
    res.json(user);
  } else {
    res.status(404).json({ error: "Utilisateur non trouvé" });
  }
});

app.listen(3000, () => {
  console.log("🚀 Serveur démarré sur http://localhost:3000");
});
