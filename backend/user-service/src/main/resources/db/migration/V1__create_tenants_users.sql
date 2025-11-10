-- enable uuid generator (Postgres)
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE tenants (
                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                         name VARCHAR(100) NOT NULL,
                         email VARCHAR(100),
                         phone VARCHAR(30),
                         domain VARCHAR(100),
                         plan VARCHAR(20) DEFAULT 'FREE',
                         metadata JSONB DEFAULT '{}'::jsonb,
                         created_at TIMESTAMPTZ DEFAULT NOW()
);

CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       tenant_id UUID NOT NULL REFERENCES tenants(id) ON DELETE CASCADE,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL,
                       phone VARCHAR(30),
                       role VARCHAR(30) DEFAULT 'USER',
                       specialty VARCHAR(100),
                       profile JSONB DEFAULT '{}'::jsonb,
                       created_at TIMESTAMPTZ DEFAULT NOW(),
                       CONSTRAINT uniq_tenant_email UNIQUE (tenant_id, email)
);

CREATE INDEX idx_users_tenant ON users(tenant_id);
CREATE INDEX idx_tenants_plan ON tenants(plan);
