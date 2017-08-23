INSERT INTO users (username, enabled, password) VALUES (
  'admin', TRUE, '$2a$06$8syqpRyGUz9nBxFp26w1gu.xIrpsSnhT6Axn8R3gHuPULTtb3EOvK'
);

INSERT INTO user_role (username, role) VALUES (
  'admin', 'ROLE_ADMIN'
);