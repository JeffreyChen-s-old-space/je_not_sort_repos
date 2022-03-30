class Collision:
    # 判斷是否碰撞類別
    def __init__(self):
        self.Now_Collision = False

    def Is_Collision(self, Object_X, Object_Y, Object_Width, Object_Height, Collision_X, Collision_Y, Collision_Width,
                     Collision_Heihgt, id="id"):
        self.id = id
        '''
        Object_x<(Collision_x+Collision_w)&&Object_y<(Collision_y+Collision_h)
        &&(Collision_x<Object_x+Object_w)&&Collision_y<(Object_y+Object_h)
        '''
        # 用條件判斷是否碰撞
        if (Object_X < (Collision_X + Collision_Width) and Object_Y < (Collision_Y + Collision_Heihgt)
                and (Collision_X < Object_X + Object_Width) and Collision_Y < (Object_Y + Object_Height)):
            Width = (Object_X + Object_Width) - Collision_X
            Height = (Object_Y + Object_Height) - Collision_Y
            # 碰撞區域大於20%才算
            if (Width * Height / (Collision_Width * Collision_Heihgt) >= 0.20):
                self.Now_Collision = True
        return self.Now_Collision
