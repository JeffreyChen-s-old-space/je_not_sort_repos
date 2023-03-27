# TestApi

To start your Phoenix server:

* Install dependencies with `mix deps.get`
* Create and migrate your database with `mix ecto.setup`
* Start Phoenix endpoint with `mix phx.server` or inside IEx with `iex -S mix phx.server`

Now you can visit [`localhost:4000`](http://localhost:4000) from your browser.

Ready to run in production? Please [check our deployment guides](https://hexdocs.pm/phoenix/deployment.html).

## should install elixir and phx first

### step1:

open elixir main page and download stable ver & install.

### step2:

add elixir to path (install option)

### step3 install hex use cmd:

```
mix local.hex
```

### step4 install phx  use cmd

```
mix archive.install hex phx_new
```

### step5 install PostgreSQL

open PostgreSQL main page and download & install

### step 6 setting PostgreSQL

open PostgreSQL pgAdmin then login use install setting user and password
and left click Server on left panel
and left click PostgreSQL (your version) on left panel
add a database name is test_api_dev
and right click test_api_dev database on left panel
choose restore and choose test_api_backup
then click restore on dialog

### step 7 open cmd and cd to project

```
cd "project_path/test_api"
```

### step 8 open web api

```
mix phx.server
```

now server run on localhost:4000

## Learn more

* Official website: https://www.phoenixframework.org/
* Guides: https://hexdocs.pm/phoenix/overview.html
* Docs: https://hexdocs.pm/phoenix
* Forum: https://elixirforum.com/c/phoenix-forum
* Source: https://github.com/phoenixframework/phoenix