import { serve } from "@hono/node-server";
import { Hono } from "hono";
import { cors } from "hono/cors";
import { logger } from "hono/logger";

export const app = new Hono();

app.use("*", cors());
app.use(logger());

app.post("*", async (c) => {
  const body = await c.req.json();
  console.log("body", JSON.stringify(body, null, 2));
  return c.json({ ok: true });
});

app.get("*", async (c) => {
  return c.json({ ok: true });
});

serve(app, (info) => {
  console.log(`Listening on http://localhost:${info.port}`);
});
