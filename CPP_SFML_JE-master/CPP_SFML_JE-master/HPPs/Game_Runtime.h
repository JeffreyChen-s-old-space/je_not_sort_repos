#pragma once
#include <SFML/Graphics.hpp>

using namespace sf;

class Game_Runtime
{
public:

	Game_Runtime(const Game_Runtime&) = delete;
	Game_Runtime& operator=(const Game_Runtime&) = delete;
	Game_Runtime();
	void Run();


private:
	void Event();
	void Update(sf :: Time);
	void Render();
	sf::RenderWindow Game_Window;
};


