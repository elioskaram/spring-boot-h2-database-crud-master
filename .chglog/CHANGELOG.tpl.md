{{ if .Versions -}}
  {{ range .Versions -}}
    {{ if .Tag -}}
## [{{ .Tag.Name }}] - {{ datetime "2006-01-02" .Tag.Date }}
    {{ else -}}
## [Unreleased]
    {{ end -}}
    {{ range .CommitGroups -}}
### {{ .Title }}

      {{ range .Commits -}}
- {{ .Subject }} ([{{ .Hash.Short }}]({{ $.Info.RepositoryURL }}/commit/{{ .Hash.Long }}))
      {{ end -}}
    {{ end -}}
    {{ if .NoteGroups -}}
      {{ range .NoteGroups -}}
### {{ .Title }}

        {{ range .Notes -}}
{{ .Body }}
        {{ end -}}
      {{ end -}}
    {{ end -}}
  {{ end -}}
{{ end -}}
