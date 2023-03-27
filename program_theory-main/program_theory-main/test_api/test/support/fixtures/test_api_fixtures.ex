defmodule TestApi.Test_apiFixtures do
  @moduledoc """
  This module defines test helpers for creating
  entities via the `TestApi.Test_api` context.
  """

  @doc """
  Generate a post.
  """
  def post_fixture(attrs \\ %{}) do
    {:ok, post} =
      attrs
      |> Enum.into(%{
        article: "some article",
        content: "some content"
      })
      |> TestApi.Test_api.create_post()

    post
  end
end
