import {FormControl, FormField, FormItem, FormLabel, FormMessage} from "@/components/ui/form.tsx";
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select.tsx";
import {Input} from "@/components/ui/input.tsx";
import {Popover, PopoverContent, PopoverTrigger} from "@/components/ui/popover.tsx";
import {Button} from "@/components/ui/button.tsx";
import {format} from "date-fns";
import {CalendarIcon} from "@radix-ui/react-icons";
import {Calendar} from "@/components/ui/calendar.tsx";
import {Checkbox} from "@/components/ui/checkbox.tsx";
import {DialogClose, DialogFooter} from "@/components/ui/dialog.tsx";
import {Instituicao} from "@/api/model/Instituicao.ts";


export type FormSharedProps = {
    form: unknown,
    onSubmit: () => void,
    instituicoes: Instituicao[]
}
export function FormShared({form, onSubmit, instituicoes} : FormSharedProps) {
    return (
        <form onSubmit={form.handleSubmit(onSubmit)}>
            <FormField
                control={form.control}
                name="instituicao"
                render={({field}) => (
                    <FormItem>
                        <FormLabel>Instituição</FormLabel>
                        <Select
                            onValueChange={(value) => {
                                const selectedInstituicao = instituicoes.find(inst => inst.nome === value);
                                field.onChange(selectedInstituicao);
                            }}
                            defaultValue={field.value?.nome}
                        >
                            <FormControl>
                                <SelectTrigger>
                                    <SelectValue placeholder="Selecione uma instituição"/>
                                </SelectTrigger>
                            </FormControl>
                            <SelectContent>
                                {instituicoes.map((instituicao) => (
                                    <SelectItem key={instituicao.id} value={instituicao.nome}>
                                        {instituicao.nome}
                                    </SelectItem>
                                ))}
                            </SelectContent>
                        </Select>
                        <FormMessage/>
                    </FormItem>
                )}
            />

            <FormField control={form.control} name={"name"}
                       render={({field}) => (
                           <FormItem className={"mt-2"}>
                               <FormLabel>Nome do Evento</FormLabel>
                               <FormControl>
                                   <Input placeholder="Nome do evento" {...field}/>
                               </FormControl>
                               <FormMessage/>
                           </FormItem>
                       )}
            />

            <FormField control={form.control} name={"dataInicial"}
                       render={({field}) => (
                           <FormItem className="flex flex-col mt-3">
                               <FormLabel>Data Inicial do evento</FormLabel>
                               <Popover>
                                   <PopoverTrigger asChild>
                                       <FormControl>
                                           <Button variant={"outline"}>
                                               {field.value ? (
                                                   format(field.value, "PPP")
                                               ) : (
                                                   <span>Pick a date</span>
                                               )}
                                               <CalendarIcon className="ml-auto h-4 w-4 opacity-50"/>
                                           </Button>
                                       </FormControl>
                                   </PopoverTrigger>
                                   <PopoverContent className="w-auto p-0" align="start">
                                       <Calendar
                                           mode="single"
                                           selected={field.value}
                                           onSelect={field.onChange}
                                           disabled={(date) => date < new Date("2000-01-01")
                                           }
                                           initialFocus
                                       />
                                   </PopoverContent>
                               </Popover>
                               <FormMessage/>
                           </FormItem>
                       )}
            />

            <FormField control={form.control} name={"dataFinal"}
                       render={({field}) => (
                           <FormItem className="flex flex-col mt-3">
                               <FormLabel>Data Final</FormLabel>
                               <Popover>
                                   <PopoverTrigger asChild>
                                       <FormControl>
                                           <Button variant={"outline"}>
                                               {field.value ? (
                                                   format(field.value, "PPP")
                                               ) : (
                                                   <span>Pick a date</span>
                                               )}
                                               <CalendarIcon className="ml-auto h-4 w-4 opacity-50"/>
                                           </Button>
                                       </FormControl>
                                   </PopoverTrigger>
                                   <PopoverContent className="w-auto p-0" align="start">
                                       <Calendar
                                           mode="single"
                                           selected={field.value}
                                           onSelect={field.onChange}
                                           disabled={(date) =>
                                               date < new Date("2000-01-01")
                                           }
                                           initialFocus
                                       />
                                   </PopoverContent>
                               </Popover>
                               <FormMessage/>
                           </FormItem>
                       )}
            />

            <FormField control={form.control} name={"ativo"}
                       render={({field}) => (
                           <FormItem className={"mt-2"}>
                               <FormLabel>Evento Ativo</FormLabel>
                               <FormControl>
                                   <Checkbox checked={field.value} onCheckedChange={field.onChange}
                                             className={"ml-2"}/>
                               </FormControl>
                               <FormMessage/>
                           </FormItem>
                       )}
            />
            <DialogFooter>
                <DialogClose asChild>
                    <Button variant="secondary">Cancel</Button>
                </DialogClose>
                <Button type="submit">Salvar Alterações</Button>
            </DialogFooter>
        </form>

    )
}