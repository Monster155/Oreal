package ru.itlab.oreal.Utils;

import com.badlogic.gdx.utils.Array;

public class DialogsList {
    public static Array<DialogHolder> getDialog(int dialogNumber, int mapNumber) {
        Array<DialogHolder> holder = new Array<DialogHolder>();
        switch (mapNumber) {
            case 1:
                switch (dialogNumber) {
                    case 1:
                        holder.add(new DialogHolder("Бродячих прошу оставить на улице!", false));
                        holder.add(new DialogHolder("Этот еще щенком со мной! Он из служивых! Простудится ведь...", true));
                        holder.add(new DialogHolder("У меня здесь не приют. Или здесь, или вынуждены прощаться с вами.", false));
                        break;
                    case 2:
                        holder.add(new DialogHolder("Полисменов я уже вызвал. Скорей бы этот ужас прошел. " +
                                "Столько проблем из-за одной паршивки. " +
                                "Буду краток: нашел я ее лежащей без сознания посреди туалетной комнаты. " +
                                "Я давно подозревал, что она не верна мне, видимо Бог решил покарать. " +
                                "Вы могли подумать на меня – можете отбросить эти мысли. ", false));
                        holder.add(new DialogHolder("Да, в последнее время женщина в доме была большой обузой, " +
                                "но мой статус не позволил бы мне тронуть человека. " +
                                "А теперь прошу меня простить, вынужден удалиться…", false));
                        break;
                    case 3:
                        holder.add(new DialogHolder("Не знаете ли, покидал кто-нибудь этот дом в последние пару часов?", true));
                        holder.add(new DialogHolder("Да как же не знать. Не выходил никто! Взгляните в окно! " +
                                "Да и за задними воротами никого не наблюдала…", false));
                        holder.add(new DialogHolder("*Тихо* Значит ли это, что убийца все еще может быть здесь?", true));
                        holder.add(new DialogHolder("Я что-то слышу!", true));
                        break;
                }
                break;
            case 2:
                switch (dialogNumber) {
                    case 1:
                        holder.add(new DialogHolder("Где я?..", true));
                        holder.add(new DialogHolder("Очнулся наконец?! Спокойно, не суетись. " +
                                "Я подобрал тебя свалившегося без сознания в лесу. Как только ты оказался там?", false));
                        holder.add(new DialogHolder("А собаку? Видел? Я гнался за ней и потерял из виду", true));
                        holder.add(new DialogHolder("Я не видел. Возможно городничий что-то знает. Он у нас обо всем в городе ведает! " +
                                "*Смешок* Меня кстати Боб зовут. " +
                                "Если помощь нужна будет – проси. " +
                                "Приютить не смогу, но вот с хворостом – запросто! Я тут дровосеком работаю.", false));
                        break;
                    case 2:
                        holder.add(new DialogHolder("Помочь чем?", true));
                        holder.add(new DialogHolder("А силенок хватит? " +
                                "*Смешок* Крыша течет уже второй день, а разобраться никак не могу!", false));
                        holder.add(new DialogHolder("Да помогу чем смогу!", true));
                        break;
                    case 3:
                        holder.add(new DialogHolder("А ты мастер на все руки! Неужто плотником работаешь?", false));
                        holder.add(new DialogHolder("Нет, к сожалению. Отец отдал учиться в академию. " +
                                "А у самого душа к рукоделию лежит. " +
                                "Дело тут расследую. Не слышал об убийстве жены чиновника?", true));
                        holder.add(new DialogHolder("Да слухи разные ходят, но дела мне до них никакого. " +
                                "Здорово помог ты мне! Даже не знаю, как благодарить", false));
                        holder.add(new DialogHolder("Услуга за услугу", true));
                        break;
                    case 4:
                        holder.add(new DialogHolder("Это ты тот страдалец! Мне о тебе уже доложили." +
                                "Видели мы твоего пса. Убежал с каким-то мужиком седым в сторону деревни.", false));
                        holder.add(new DialogHolder("*Удивлен* А как мне добраться до нее?!", true));
                        holder.add(new DialogHolder("До нее 3 дня ходу. " +
                                "Советую сходить к кучеру, авось он докинет, если деньги при себе", false));
                        holder.add(new DialogHolder("Поинтересоваться хотел, не слышали ли вы об убийстве…", true));
                        holder.add(new DialogHolder("Некогда мне болтать!", false));
                        break;
                    case 5:
                        holder.add(new DialogHolder("Добрый день!" +
                                "Можете меня подкинуть до соседней деревни?", true));
                        holder.add(new DialogHolder("Да мне самому бы туда добраться, жаль только колеса у телеги поломало во время грозы." +
                                "Теперь не уехать, а новых ждать еще с неделю", false));
                        holder.add(new DialogHolder("А если починю твои колеса, подкинешь за бесценок?", true));
                        holder.add(new DialogHolder("Да хоть на крайний Север! Не нравится мне этот город…", false));
                        break;
                    case 6:
                        holder.add(new DialogHolder("Ещё раз здравствуй! Мне снова нужна твоя помощь." +
                                "Извозчик просил меня починить его телегу." + "" +
                                "Можешь сделать мне новое колесо?", true));
                        holder.add(new DialogHolder("Без проблем! А пока я за бревнами схожу, окажи мне услугу. " +
                                "Часы сломались, которые от прабабки моей достались. Очень ценная реликвия. " +
                                "Если возьмешься за починку – буду благодарен!", false));
                        break;
                    case 7:
                        holder.add(new DialogHolder("Спасибо за колесо, мне пора.", true));
                        break;
                    case 8:
                        holder.add(new DialogHolder("Держи изделие! Нам надо спешить", false)); //какой-то придурошный писал тру и фалс путает
                        holder.add(new DialogHolder("Премного благодарен. Поехали скорее, а то в последние пару дней" +
                                " с городничим явно что-то не так", false));
                        break;
                }
                break;

            case 3:
                switch (dialogNumber) {
                    case 1:
                        // прощание с кучером
                        holder.add(new DialogHolder("Ну все, мы на месте. Удачи в поисках", false));
                        holder.add(new DialogHolder("И тебе не хворать, хорошей дороги...", true));
                        break;
                    case 2:
                        // встречает незнакомца и узнает куда идти
                        holder.add(new DialogHolder("Прошу прощения, а в каком доме проживает Стивен Роджердсон?", true));
                        holder.add(new DialogHolder("Хм.. Если мне не изменяет память, то в доме справа через дорогу", false));
                        holder.add(new DialogHolder("Спасибо Вам, добрый человек.", true));
                        break;
                    case 3:
                        holder.add(new DialogHolder("Вы - Стивен Роджердсон?", true));
                        holder.add(new DialogHolder("К счастью, нет. А по какому делу он Вам нужен?", false));
                        holder.add(new DialogHolder("Дело в том, что у меня пропала собака и городничий N-ого города" +
                                "утверждал, что он видели моего пса", true));
                        holder.add(new DialogHolder("Хммм... Он пропал вчера днем. Я могу, конечно, Вам помочь" +
                                "но само собой не бесплатно", false));
                        holder.add(new DialogHolder("Я даже не знаю, меня обворовали пару дней назад, ввиду чего у меня " +
                                "есть некий дифицит денежный средств. Однако, я готов на все ради " +
                                "информации о своем верном друге.", true));
                        holder.add(new DialogHolder("Так. Ну есть у меня одна сломанная шкатулка - там сломался" +
                                "замок и если поможешь с ним, то расскажу что знаю о твоем псе", false));
                        holder.add(new DialogHolder("Хмм. Ну давай-ка попробую *Слегка посмеиваясь*", true));
                        break;
                    case 4:
                        holder.add(new DialogHolder("Ого! Да у тебя золотые руки! Хорошо, помогу как обещал.", false));
                        holder.add(new DialogHolder("Знакомый мой видел, как твоя собака запрыгнула в поезд вслед за каким-то музчиной." +
                                " Это все, что известно...", false));
                        holder.add(new DialogHolder("И на этом спасибо!", true));
                        break;
                    case 5:
                        // Разговор с работницей станции => пес укатил
                        holder.add(new DialogHolder("Девушка, простите! Вы не видели здесь вчера-сегодня молодого человека с собакой?", true));
                        holder.add(new DialogHolder("Да, видела. Вчера запрыгнул в почтой вагон поезда," +
                                " который направлялся в город M", false));
                        holder.add(new DialogHolder("А как до того города добраться?", true));
                        holder.add(new DialogHolder("Ближайший поезд должен будет быть через 35 минут, но он не сможет отправиться, т.к." +
                                "сломаны сцепления для вагонов. И как на зло главный инженер приболел", false));
                        holder.add(new DialogHolder("Давайте я попробую что-нибудь сделать, отец учил меня их изготавливать", true));
                        break;
                }
                break;

            case 4:
                switch (dialogNumber) {
                    case 1: //Встречаем на вокзале челика, который знает Стивена Роджерсона
                        holder.add(new DialogHolder("Уважаемый! Я ищу Стивена Роджерсона, не знаете где он может быть?", true));
                        holder.add(new DialogHolder("Он живет на другом конце города, надо пройти через лес..", false));
                        holder.add(new DialogHolder("А Вы не могли бы составить мне компанию?", true));
                        holder.add(new DialogHolder("Я бы с радостью, но не могу. Я не привык бросать дела на половине." +
                                "Я придумываю собственную систему канализации и в схеме допущена " +
                                "ошибка, вот уже который час думаю, где мог ошибиться", false));
                        holder.add(new DialogHolder("Можно взгляну, если вы не против?", true));
                        holder.add(new DialogHolder("*Протягивает схему детективу*", false));
                        // исправляем ошибки....
                        holder.add(new DialogHolder("Как же я сам не догадался. Всегда так..", false));
                        holder.add(new DialogHolder("Ну что ж, раз помог - пойдем, провожу.", false));
                        break;
                    case 2:
                        holder.add(new DialogHolder("*Кричит* Стивен Роджерсон! Мне нужно с Вами поговорить!", true));
                        holder.add(new DialogHolder("Зачем я Вам?", false));
                        holder.add(new DialogHolder("Городничий города Х, сообщил, что Вы могли видеть мою собаку позавчера," +
                                " когда уезжали", true));
                        holder.add(new DialogHolder("*Нервничая* Ааа. Я ее в лесу недавно видел, она взялась охранять одну пещеру" +
                                " давайте провожу Вас туда, а то небось заблудитесь...", false));
                        //В лесу происходит нападение и из-за навыков детектива ГГ остается в живых.
                        break;
                    case 3:
                        holder.add(new DialogHolder("Тысяча чертей! Револьвер вышел из строя...", true));
                        break;
                    case 4:
                        holder.add(new DialogHolder("Зачем Вы напали на меня? И что с моей собакой?", true));
                        holder.add(new DialogHolder("Мне угрожал городовой и сказал, что в скором времени" +
                                "придет кто-то, кто будет искать свою собаку собаки и ради того, чтобы" +
                                " все мои любимые и близкие оставались в неприкосновенности" +
                                " я должен был убить этого человека.", false));
                        holder.add(new DialogHolder("Мне очень стыдно за свои действия, но у меня не было" +
                                " другого выхода. А по поводу собаки, мне кажется, " +
                                "что городничий убил ее", false));
                        holder.add(new DialogHolder("Прости уж, но мне придется тебя арестовать..." +
                                "Но я могу тебе гарантировать, что он ответит за свои деяния", true));
                        // Чиним револьвер
                        break;
                }
        }
        return holder;
    }
}
