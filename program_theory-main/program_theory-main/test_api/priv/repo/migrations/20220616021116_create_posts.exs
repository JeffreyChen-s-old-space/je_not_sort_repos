defmodule TestApi.Repo.Migrations.CreatePosts do
  use Ecto.Migration

  def change do
    create table(:posts) do
      add :article, :text
      add :content, :text

      timestamps()
    end
  end
end
