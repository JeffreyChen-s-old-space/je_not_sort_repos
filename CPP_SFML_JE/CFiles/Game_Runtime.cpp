#include "Game_Runtime.h"

Game_Runtime :: Game_Runtime() : Game_Window(VideoMode(800, 600), "Test") {

}

void Game_Runtime :: Run()
{

	sf :: Clock clock;

	while (Game_Window.isOpen())
	{
		Event();
		Update(clock.restart());
		Render();
	}
}

void Game_Runtime::Event() 
{
	sf::Event event;
	while (Game_Window.pollEvent(event)) {
		switch (event.type) 
		{
			case  sf::Event::EventType::Closed:
				Game_Window.close();
				break;

			case sf::Event::EventType::KeyPressed:
				if (event.key.code == sf::Keyboard::Key::Escape) 
				{
					Game_Window.close();
				}
				break;

			default:
				break;
		}
	}
}


void Game_Runtime :: Update(sf :: Time Deltatime)
{
}

void Game_Runtime :: Render()
{
	Game_Window.clear();
	Game_Window.display();
}
